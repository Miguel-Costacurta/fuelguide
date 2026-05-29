import type { RotaResponse } from "../models/RotaResponse";
import type { TipoCombustivel } from "../models/TipoCombustivel";
import ResultadoRota from "../components/ResultadoRota";
import { calcularRota } from "../services/rotaService";
import {useState} from "react";

function HomePage(){
    const [carregando, setCarregando] = useState(false);
    const [erro, setErro] =  useState("");

    const [origem, setOrigem] = useState("");
    const [destino, setDestino] = useState("");
    const [autonomiaKm, setAutonomiaKm] = useState("");
    const [capacidadeLitros, setCapacidadeLitros] = useState("");
    const [combustivel, setCombustivel] = useState<TipoCombustivel>("ETANOL");
    const [nivelAtualPct, setNivelAtualPct] = useState("");

    const [resultado, setResultado] = useState<RotaResponse | null>(null);

    async function handleTestarRota(){
        
        if(!origem || !destino){
            setErro("Origem e destino são obrigátorios!")
            return;
        }

        if(Number(autonomiaKm) <= 0 || Number(capacidadeLitros) <= 0 || Number(nivelAtualPct) <= 0){
            setErro("Informações inválidas");
            return;
        }
        
        setErro("");
        setResultado(null);

        try{
            setCarregando(true)

            const response = await calcularRota({
               cidadeOrigem: origem,
               cidadeDestino: destino,
               combustivel: combustivel,
               autonomiaKm: Number(autonomiaKm),
               capacidadeLitros: Number(capacidadeLitros),
               nivelAtualPct: Number(nivelAtualPct)
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
            
            <label htmlFor="TipoCombustivel">
                <select value={combustivel} required onChange={(e) => setCombustivel(e.target.value as TipoCombustivel)}>
                <option value="ETANOL">Etanol</option>
                <option value="GASOLINA">Gasolina Comum</option>
                <option value="GASOLINA_ADITIVADA">Gasolina Aditivada</option>
                <option value="GNV">GNV</option>
                <option value="DIESEL_S10">Diesel S10</option>
            </select>
            </label>
            
            <input placeholder="Nivel do Tanque" 
            value={nivelAtualPct}
            onChange={(e) => setNivelAtualPct(e.target.value)}
            />

            <input placeholder="Autonomia" 
            value={autonomiaKm}
            onChange={(e) => setAutonomiaKm(e.target.value)}
            />

            <input placeholder="Capacidade do tanque" 
            value={capacidadeLitros}
            onChange={(e) => setCapacidadeLitros(e.target.value)}
            />
            

            <button onClick={handleTestarRota} disabled={carregando}>
                Calcular Rota
            </button>
            {carregando && <p>Calculando rota...</p>}
            {erro && <p>{erro}</p>}
            {resultado && <ResultadoRota resultado={resultado}/>}

        </main>
    );
}


export default HomePage;