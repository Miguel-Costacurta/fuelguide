import type { RotaResponse } from "../models/RotaResponse";
import ResultadoRota from "../components/ResultadoRota";
import { calcularRota } from "../services/rotaService";
import { useState } from "react";
import type { RotaRequest } from "../models/RotaRequest";
import { RotaForm } from "../components/RotaForm";
import type { PostoRecomendado } from "../models/PostoRecomendado";
import MapaRota from "../components/MapaRota";

function HomePage(){
    const [carregando, setCarregando] = useState(false);
    const [erro, setErro] =  useState("");
    const [postoSelecionado, setPostoSelecionado] = useState<PostoRecomendado | null>(null);

    const [resultado, setResultado] = useState<RotaResponse | null>(null);

    async function handleTestarRota(dados: RotaRequest){
        setErro("");

        try{
            setCarregando(true)

            const response = await calcularRota({
               cidadeOrigem: dados.cidadeOrigem,
               cidadeDestino: dados.cidadeDestino,
               combustivel: dados.combustivel,
               autonomiaKm: dados.autonomiaKm,
               capacidadeLitros: dados.capacidadeLitros,
               nivelAtualPct: dados.nivelAtualPct,
               prioridade: dados.prioridade,
         });

         console.log(response)
         setResultado(response)
        } catch(error) {
            
            setErro("Erro ao calcular rota");
            console.log(error)

        }finally{
            setCarregando(false);
        }
    }

    return (
  <main className="h-screen flex bg-gray-900 text-white">
    {!resultado ? (
      // 🔵 TELA INICIAL
      <div className="m-auto w-full max-w-md text-center px-4">
        <h1 className="text-4xl font-bold mb-2">Fuel Guide</h1>
        <p className="text-gray-400 mb-6">
          Planeje sua viagem com segurança
        </p>

        <div className="bg-gray-800 rounded-2xl p-6 shadow-lg">
          <RotaForm onCalcular={handleTestarRota} loading={carregando} />

          {erro && (
            <p className="text-red-400 mt-4 text-sm">{erro}</p>
          )}
        </div>
      </div>
    ) : (
      // 🟢 APP COMPLETO
      <>
        {/* 🔹 SIDEBAR */}
        <aside className="w-[320px] h-full bg-gray-800 border-r border-gray-700 flex flex-col">
          
          {/* FORM */}
          <div className="p-4 border-b border-gray-700">
            <button
            onClick={() => setResultado(null)}
            className="bg-gray-700 hover:bg-gray-600 p-2 rounded-lg w-full">
                Calcular nova rota
            </button>
          </div>

          {/* STATUS */}
          <div className="p-4 space-y-2">
            {carregando && (
              <p className="text-yellow-400 text-sm">Calculando rota...</p>
            )}

            {erro && (
              <p className="text-red-400 text-sm">{erro}</p>
            )}
          </div>

          {/* RESULTADO */}
          <div className="flex-1 overflow-y-auto p-4">
            <ResultadoRota resultado={resultado} 
            selecionado={postoSelecionado}
            onSelecionar = {setPostoSelecionado}/>
          </div>
        </aside>

        {/* 🔹 MAPA */}
        <section className="flex-1 relative">
          <div className="absolute inset-0 flex items-center justify-center text-gray-500">
            <MapaRota
            resultado={resultado}
            selecionado={postoSelecionado}
            onSelecionar={setPostoSelecionado}
            />
          </div>
        </section>
      </>
    )}
  </main>
);
}


export default HomePage;