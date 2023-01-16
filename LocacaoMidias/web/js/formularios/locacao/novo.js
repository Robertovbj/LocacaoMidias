// array para armazenar os itens da venda
let itensLocacao = [];

// formatadores
let fmtMoeda = new Intl.NumberFormat( 
    "pt-BR", {
        style: "currency",
        currency: "BRL"
    }
);

let fmtNumero = new Intl.NumberFormat( 
    "pt-BR", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }
);

function inserirMidia( event ) {

    let $exemplar = $( event.target );
    let idExemplar = $exemplar.data( "id" ).toString();
    let idMidia = $exemplar.data( "idmidia" );
    let titulo = $exemplar.data( "titulo" );
    let valor = $exemplar.data( "valor" );

    // se o valor da venda tem vírgula, troca por ponto
    if ( valor.includes( "," ) ) {
        valor = valor.replace( ",", "." );
    }

    // há um item da venda igual?
    let itemIgual = null;
    itensLocacao.some( item => {
        if ( item.idMidia === idMidia ) {
            itemIgual = item;
            return true; // para a iteração
        }
    });

    // se há item igual, avisa
    if ( itemIgual !== null ) {

        alert("Não é possível alugar dois exemplares da mesma mídia!");

    // caso contrário, cria um novo item
    } else {
        
        itensLocacao.push({
            idExemplar: idExemplar,
            valor: valor,
            titulo: titulo,
            idMidia: idMidia
        });
        
         atualizarGUI();
    }                
    
}

function remover( event ) {
    let $item = $( event.target ).parent();
    let idExemplar = $item.data("id").toString();
    
    for ( let j = 0; j < itensLocacao.length; j++ ) {
                    
        let item = itensLocacao[j];

        // encontrou?
        if ( idExemplar === item.idExemplar ) {

            // remove da posição j
            itensLocacao.splice( j, 1 );
            break;

        }

    }
    
    // remonta a lista
    atualizarGUI();
}

// constrói as opções do <select> (lista) de itens de venda;
// atualiza o valor total da venda;
// e prepara os dados para envio
let atualizarGUI = () => {

    let $itens = $( "#itens-selecionados" );
    let total = new Decimal( 0 );

    $itens.html( "" );

    itensLocacao.forEach( item => {

        let valorItem = new Decimal( item.valor );

        $itens.append( 
                `<tr data-id="${item.idExemplar}"><td class="valor">${fmtMoeda.format( valorItem )}</td>` +
                `<td class="itens">${item.titulo}</td>` +
                `<td class="remover" onclick="remover( event )">Remover</td></tr>` );

        total = total.plus( valorItem );

    });

        $( "#divTotal" ).html( "Total: " + fmtMoeda.format( total ) );
        $( "#hiddenItensLocacao" ).val( JSON.stringify( itensLocacao ) );

};