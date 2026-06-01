import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import type { RotaResponse } from "../models/RotaResponse";
import type { PostoRecomendado } from "../models/PostoRecomendado";

interface Props {
    resultado: RotaResponse;
    selecionado: PostoRecomendado | null;
    onSelecionar: (posto: PostoRecomendado) => void;
}

export default function MapaRota({ resultado, selecionado, onSelecionar}: Props){
    const centro = resultado.paradas[0]
    ? [resultado.paradas[0].lat, resultado.paradas[0].lon]
    : [-23.4, -46.6];

    return(
        <MapContainer
        center={centro as [number, number]}
        zoom={7}
        className="w-full h-full"
        >
            <TileLayer
            attribution="&copy; OpenStreetMap"
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {resultado.postosNaRota.map((posto, index) => (
                <Marker
                key={posto.nome + index}
                position={[posto.lat, posto.lon]}
                eventHandlers={{
                    click: () => onSelecionar(posto),
                }}
                >
                    <Popup>
                        <strong>{posto.nome}</strong><br />
                        <strong>{posto.cidade} - {posto.estado}</strong><br />
                        R$ {posto.preco.toFixed(2)}
                    </Popup>
                </Marker>
            ))}
        </MapContainer>
    )
}