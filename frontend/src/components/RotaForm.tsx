import { useState } from "react";
import type { TipoCombustivel } from "../models/TipoCombustivel";
import type { RotaRequest } from "../models/RotaRequest";

type Props = {
    onCalcular: (dados: RotaRequest) => void | Promise<void>;
    loading: boolean;
};

export function RotaForm({onCalcular, loading}: Props) {
    const [origem, setOrigem] = useState("");
    const [destino, setDestino] = useState("");
    const [autonomia, setAutonomiaKm] = useState("");
    const [capacidadeLitros, setCapacidadeLitros] = useState("");
    const [combustivel, setCombustivel] = useState<TipoCombustivel>("ETANOL");
    const [nivelAtualPct, setNivelAtualPct] = useState("");
    const [prioridade, setPrioridade] = useState("EQUILIBRADO");

    const [erro, setErro] = useState("");

    function handleSubmit(e: React.FormEvent){
        e.preventDefault();

        if(!origem || !destino){
            setErro("Origem e destino são obrigátorios!")
            return;
        }

        if(Number(autonomia) <= 0 || Number(capacidadeLitros) <= 0 || Number(nivelAtualPct) <= 0){
            setErro("Informações inválidas");
            return;
        }

        

        onCalcular({
            cidadeOrigem: origem,
            cidadeDestino: destino,
            combustivel,
            autonomiaKm: Number(autonomia),
            capacidadeLitros: Number(capacidadeLitros),
            nivelAtualPct: Number(nivelAtualPct),
            prioridade: prioridade,
        });
    }

    return(
        <form
            onSubmit={handleSubmit}
            className="grid grid-cols-2 gap-3 p-4"
        >
            <h2 className="text-lg font-semibold">Planejar Rota</h2>

            <input placeholder="Origem"
            value={origem}
            onChange={(e) => setOrigem(e.target.value)} 
            className="col-span-2 border rounded-lg p-2 w-full"/>

            <input placeholder="Destino"
            value={destino}
            onChange={(e) => setDestino(e.target.value)} 
            className="col-span-2 border rounded-lg p-2 w-full"/>

            <select
                value={combustivel}
                onChange={(e) => setCombustivel(e.target.value as TipoCombustivel)}
                className="border rounded-lg p-2 w-full"
            >
                <option value="GASOLINA">Gasolina</option>
                <option value="ETANOL">Etanol</option>
                <option value="DIESEL">Diesel</option>
                <option value="FLEX">Flex</option>
            </select>

            <input
                type="number"
                placeholder="Autonomia (km)"
                value={autonomia}
                onChange={(e) => setAutonomiaKm((e.target.value))}
                className="grid grid-cols-3 gap-3 p-4"
            />

            <input
                type="number"
                placeholder="% tanque atual"
                value={nivelAtualPct}
                onChange={(e) => setNivelAtualPct((e.target.value))}
                className="grid grid-cols-3 gap-3 p-4"
            />

            <input
                type="number"
                placeholder="Capacidade (litros)"
                value={capacidadeLitros}
                onChange={(e) => setCapacidadeLitros((e.target.value))}
                className="grid grid-cols-3 gap-3 p-4"
            />

            <select
                value={prioridade}
                onChange={(e) => setPrioridade(e.target.value)}
                className="border rounded-lg p-2 w-full"
            >
                <option value="DISTANCIA">Distancia</option>
                <option value="ECONOMIA">Economia</option>
                <option value="EQUILIBRADO">Equilibrado</option>
            </select>

            <button
                type="submit"
                disabled={loading}
                className="bg-blue-600 text-white rounded-lg p-3 font-semibold hover:bg-blue-700 transition disabled:opacity-50"
            >
                {loading ? "Calculando..." : "Calcular rota"}
            </button>
        </form>
    )

}

