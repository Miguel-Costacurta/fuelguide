import type { PostoRecomendado } from "../models/PostoRecomendado";
import type { RotaResponse } from "../models/RotaResponse"


interface ResultadoRotaProps {
    resultado: RotaResponse;
    selecionado: PostoRecomendado | null ;
    onSelecionar: (posto: PostoRecomendado) => void;
}

function ResultadoRota({ resultado, selecionado, onSelecionar }: ResultadoRotaProps){
    return (
    <div className="space-y-6">
        {selecionado && (
            <div className="bg-blue-500/10 border border-blue-500 p-4 rounded-xl">
                <h3 className="font-bold text-lg">{selecionado.nome}</h3>
                <p className="text-gray-400">
                {selecionado.cidade} - {selecionado.estado}
                </p>

                <div className="mt-2 text-sm space-y-1">
                <p>⛽ {selecionado.combustivel}</p>
                <p>💰 R$ {selecionado.preco.toFixed(2)}</p>
                <p>📏 {selecionado.kmDaOrigem.toFixed(1)} km</p>
                <p>⚡ {selecionado.nivelAoChegar.toFixed(0)}%</p>
                </div>
            </div>
        )}


        <div>
    <h2 className="text-lg font-semibold mb-3 text-green-400">
      ⭐ Paradas recomendadas
    </h2>

    <div className="space-y-3">
      {resultado.paradas.map((posto, index) => (
        <div
          key={index}
          onClick={() => onSelecionar(posto)}
          className={`p-4 rounded-xl border cursor-pointer transition ${
            selecionado?.nome === posto.nome
              ? "border-blue-500 bg-blue-500/20"
              : "border-gray-600 bg-gray-800 hover:bg-gray-700"
          }`}
        >
          <div className="flex justify-between items-center">
            <h3 className="font-bold">{posto.nome}</h3>

            {index === 0 && (
              <span className="text-xs bg-green-600 px-2 py-1 rounded">
                MELHOR
              </span>
            )}
          </div>

          <p className="text-sm text-gray-400">
            {posto.cidade} - {posto.estado}
          </p>

          <div className="grid grid-cols-2 gap-2 mt-3 text-sm">
            <p>⛽ R$ {posto.preco.toFixed(2)}</p>
            <p>📏 {posto.kmDaOrigem.toFixed(1)} km</p>
            <p>💰 R$ {posto.custoEstimado.toFixed(2)}</p>
            <p>⚡ {posto.nivelAoChegar.toFixed(0)}%</p>
          </div>
        </div>
      ))}
    </div>
  </div>

  {/* DIVISOR */}
  <div className="border-t border-gray-700"></div>

  {/* 🔥 TODOS */}
  <div>
    <h2 className="text-lg font-semibold mb-3 text-gray-300">
      ⛽ Todos os postos na rota
    </h2>

    <div className="space-y-2">
      {resultado.postosNaRota.map((posto, index) => (
        <div
          key={index}
          onClick={() => onSelecionar(posto)}
          className={`flex justify-between p-3 rounded-lg cursor-pointer transition ${
            selecionado?.nome === posto.nome
              ? "bg-blue-500/20"
              : "bg-gray-800 hover:bg-gray-700"
          }`}
        >
          <div>
            <p className="font-medium">{posto.nome}</p>
            <p className="text-gray-400 text-sm">
              {posto.cidade}
            </p>
          </div>

          <div className="text-right text-sm">
            <p>R$ {posto.preco.toFixed(2)}</p>
            <p className="text-gray-400">
              ⭐ {posto.score.toFixed(2)}
            </p>
          </div>
        </div>
      ))}
    </div>
  </div>
  </div>
    );
}

export default ResultadoRota;