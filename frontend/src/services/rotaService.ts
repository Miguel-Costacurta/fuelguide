import type { RotaResponse } from "../models/RotaResponse";
import type { RotaRequest } from "../models/RotaRequest";

export async function calcularRota(request: RotaRequest):Promise<RotaResponse> {
    const response = await fetch("http://localhost:8080/rota/calcular", {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
        },
        body: JSON.stringify(request),
    });

    if(!response.ok) {
        throw new Error("Erro ao calcular a rota");
    }

    const data = await response.json();

    return data;
}