import type { TipoCombustivel } from "./TipoCombustivel";

export interface PostoRecomendado{
    nome: string;
    cidade: string;
    estado: string;
    combustivel: TipoCombustivel
    preco: number;
    kmDaOrigem: number;
    custoEstimado: number;
    nivelAoChegar: number;
    score:number;
}