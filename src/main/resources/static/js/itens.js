document.addEventListener('DOMContentLoaded', function () {
    const elements = {
        itemCardsContainer: document.getElementById('item-cards-container'),
        itemForm: document.getElementById('item-form'),
        itemNome: document.getElementById('item-nome'),
        itemDescricao: document.getElementById('item-descricao'),
        itemPreco: document.getElementById('item-preco'),
        itemTier: document.getElementById('item-tier')
    };

    const tierColors = {
        Bronze: 'bg-bronze',
        Prata: 'bg-secondary',
        Ouro: 'bg-warning'
    };

    async function fetchAllItens() {
        try {
            const response = await fetch('/api/itens');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const allItens = await response.json();
            renderItemCards(allItens);
        } catch (error) {
            console.error("Erro ao buscar itens:", error);
            elements.itemCardsContainer.innerHTML = `<div class="col"><div class="alert alert-danger" role="alert">Não foi possível carregar os itens.</div></div>`;
        }
    }

    function renderItemCards(itens) {
        elements.itemCardsContainer.innerHTML = '';
        if (!itens || itens.length === 0) {
            elements.itemCardsContainer.innerHTML = `<div class="col"><div class="alert alert-info" role="alert">Nenhum item disponível na loja.</div></div>`;
            return;
        }

        itens.forEach(item => {
            const cardColor = tierColors[item.tier] || 'bg-light';
            elements.itemCardsContainer.innerHTML += `
                <div class="col">
                    <div class="card h-100 shadow-sm">
                        <div class="card-header ${cardColor} text-white text-center">
                            <h5 class="card-title mb-0">${item.nome}</h5>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${item.descricao}</p>
                            <p class="card-text fw-bold">Preço: <i class="fas fa-coins me-1"></i>${item.preco}</p>
                        </div>
                        <div class="card-footer text-center">
                            <span class="badge ${cardColor}">${item.tier}</span>
                        </div>
                    </div>
                </div>
            `;
        });
    }

    elements.itemForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        const newItem = {
            nome: elements.itemNome.value,
            descricao: elements.itemDescricao.value,
            preco: elements.itemPreco.value,
            tier: elements.itemTier.value
        };

        try {
            const response = await fetch('/api/itens', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(newItem)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            await fetchAllItens();
            elements.itemForm.reset();

        } catch (error) {
            console.error("Erro ao cadastrar item:", error);
            alert("Não foi possível cadastrar o item. Verifique o console para mais detalhes.");
        }
    });

    fetchAllItens();
});