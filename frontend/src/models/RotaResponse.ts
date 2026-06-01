import type { PostoRecomendado } from "./PostoRecomendado";

export interface RotaResponse {
    distanciaTotalKm: number;
    paradas: PostoRecomendado[];
    custoTotal: number;
    nivelFinalPct: number;
    alertas: string[];
    postosNaRota: PostoRecomendado[];
}