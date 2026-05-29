import type { RotaResponse } from "../models/RotaResponse"


interface ResultadoRotaProps {
    resultado: RotaResponse;
}

function ResultadoRota({ resultado }: ResultadoRotaProps){
    return (
        <div>
            <h2>Paradas Recomendadas:</h2>
            {resultado.paradas.map((posto) => (
                <div key={posto.nome + posto.kmDaOrigem}>
                    <h3>{posto.nome}</h3>
                    <p>{posto.cidade} - {posto.estado}</p>
                    <p>Preco: R$ {posto.preco}</p>
                    <p>KM: {posto.kmDaOrigem}</p>
                    <p>Custo Total: R$ {posto.custoEstimado}</p>
                </div>
            ))}
            <div>
                <h2>Resultado da rota</h2>

                <p>Distância total: {resultado.distanciaTotalKm}</p>
                <p>Custo total: {resultado.custoTotal}</p>
                <p>Nível final: {resultado.nivelFinalPct}%</p>
            </div>

        </div>
    );
}

export default ResultadoRota;