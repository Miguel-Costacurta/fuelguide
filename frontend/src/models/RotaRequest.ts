import type { TipoCombustivel } from './TipoCombustivel';

export interface RotaRequest {
    cidadeOrigem:  string;
    cidadeDestino: string;
    combustivel: TipoCombustivel;
    autonomiaKm: number;
    capacidadeLitros: number;
    nivelAtualPct: number;
    prioridade: string;
}