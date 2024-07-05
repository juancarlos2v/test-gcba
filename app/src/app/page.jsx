"use client"

import { useState } from "react";
import styles from "./page.module.css";
import { Button, Form } from "react-bootstrap";
import useAxios from "../hooks/useAxios"

export default function Home() {
  //const baseURL = "http://servicios.usig.buenosaires.gob.ar/normalizar";
  const { fetchData, response, error } = useAxios();

  const [address, setAddress] = useState([]);
  const [request, setRequest] = useState("");

  const handleChange = (event) => {
    setRequest(event.target.value);
  };
  const sendRequest = (event) => {
    event.preventDefault();
    setAddress([]);
    fetchData({
      url: `/api/normalizar/?direccion=${request}`,
      method: 'GET'
    });
    console.log(response)
    setAddress(response.direccionesNormalizadas)
  };

  return (
    <main className={styles.main}>
      <Form onSubmit={sendRequest} noValidate className="">
        <Form.Group className="">
          <Form.Label>Ingresar Direccion</Form.Label>
          <Form.Control
            type="text"
            placeholder="Calle y/o numero"
            name="direccion"
            value={request}
            onChange={handleChange}
          />
        </Form.Group>
        <Button variant="dark" type="submit" >
          Registrar
        </Button>
      </Form>

      <div>
        {
          address.map((ad, index) =>
            <div key={index}>
              <p> Direccion: {ad.direccion} </p>
              <p>Calle: {ad.nombre_calle} </p>
            </div>
          )
        }
      </div>
    </main>
  );
}
