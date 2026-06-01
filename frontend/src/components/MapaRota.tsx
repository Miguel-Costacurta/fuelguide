import { MapContainer, TileLayer, Marker, Popup, Polyline, useMap } from "react-leaflet";
import type { LatLngTuple } from "leaflet";
import type { RotaResponse } from "../models/RotaResponse";
import type { PostoRecomendado } from "../models/PostoRecomendado";
import { useEffect } from "react";

interface Props {
    resultado: RotaResponse;
    selecionado: PostoRecomendado | null;
    onSelecionar: (posto: PostoRecomendado) => void;
}

function AjustarMapaNaRota({ pontos }: {pontos: LatLngTuple[]}){
    const map = useMap();

    useEffect(() => {
        if(pontos.length > 0){
            map.fitBounds(pontos);
        }
    }, [map, pontos]);
    
    return null;
}

export default function MapaRota({ resultado, selecionado, onSelecionar}: Props){
    const pontosRota: LatLngTuple[] = resultado.coordenadasRota.map(
        (ponto) => [ponto.latitude, ponto.longitude]);
    
    const centro: LatLngTuple = pontosRota.length > 0
        ? pontosRota[0]
        : resultado.paradas[0]
        ? [resultado.paradas[0].lat, resultado.paradas[0].lon]
        : [-23.4, -46.6];

    return (
    <MapContainer center={centro} zoom={7} className="w-full h-full">
      <TileLayer
        attribution="&copy; OpenStreetMap"
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />

      {pontosRota.length > 0 && (
        <>
          <Polyline positions={pontosRota} pathOptions={{ weight: 5 }} />
          <AjustarMapaNaRota pontos={pontosRota} />
        </>
      )}

      {resultado.postosNaRota.map((posto, index) => {
        const estaSelecionado =
          selecionado?.nome === posto.nome &&
          selecionado?.kmDaOrigem === posto.kmDaOrigem;

        return (
          <Marker
            key={posto.nome + index}
            position={[posto.lat, posto.lon]}
            eventHandlers={{
              click: () => onSelecionar(posto),
            }}
          >
            <Popup>
              <strong>{posto.nome}</strong>
              <br />
              <strong>
                {posto.cidade} - {posto.estado}
              </strong>
              <br />
              R$ {posto.preco.toFixed(2)}
              <br />
              Km: {posto.kmDaOrigem.toFixed(1)}
              {estaSelecionado && (
                <>
                  <br />
                  <strong>Selecionado</strong>
                </>
              )}
            </Popup>
          </Marker>
        );
      })}
    </MapContainer>
    )
}