import { calcularRota } from "../services/rotaService";
import {useState} from "react";

function HomePage(){
    const [origem, setOrigem] = useState("");
    const [destino, setDestino] = useState("");
    const [resultado, setResultado] = useState<any>(null);

    async function handleTestarRota(){
        console.log("Clicou!")
        try{
            const response = await calcularRota({
               cidadeOrigem: origem,
              cidadeDestino: destino,
               combustivel: "ETANOL",
               autonomiaKm: 230,
               capacidadeLitros: 43,
               nivelAtualPct: 50
         });

         console.log(response)
         setResultado(response)
        } catch(error) {
            console.error(error);
        }
    }

    return (
        <main>
            <h1>Fuelguide</h1>
            <p>Planeje seu abastecimento com segurança</p>
            
            <input placeholder="Origem"
            value={origem}
            onChange={(e) => setOrigem(e.target.value)} 
            />

            <input placeholder="Destino"
            value={destino}
            onChange={(e) => setDestino(e.target.value)} 
            />

            <button onClick={handleTestarRota}>
                Calcular Rota
            </button>
            
            {resultado && (
                <div>
                    <h2>Paradas Recomendadas</h2>

                    {resultado.paradas.map((posto: any) =>(
                        <div key={posto.nome}>
                            <h3>{posto.nome}</h3>
                            <p>{posto.cidade} - {posto.estado}</p>
                            <p>Preco: R$ {posto.preco}</p>
                            <p>KM: {posto.kmDaOrigem}</p>
                            <p>Custo Total: R$ {posto.custoEstimado}</p>
                        </div>
                    ))}
                </div>
            )}

        </main>
    );
}


export default HomePage;