import type { PostoRecomendado } from "./PostoRecomendado";

export type CoordenadasRota = {
    latitude: number;
    longitude: number;
};

export interface RotaResponse {
    distanciaTotalKm: number;
    paradas: PostoRecomendado[];
    custoTotal: number;
    nivelFinalPct: number;
    alertas: string[];
    postosNaRota: PostoRecomendado[];
    coordenadasRota: CoordenadasRota[];
}