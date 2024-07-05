"use client"

import { useEffect, useState } from "react";
import styles from "./page.module.css";
import { Button, Form } from "react-bootstrap";
import useAxios from "../hooks/useAxios"

export default function Home() {
  //const baseURL = "http://servicios.usig.buenosaires.gob.ar/normalizar";
  const { fetchData, response, error } = useAxios();

  const [address, setAddress] = useState([]);
  const [request, setRequest] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const sendRequest = (event) => {
    event.preventDefault();
    setAddress([]);
    setErrorMessage("");
    fetchData({
      url: `/api/normalizar/?direccion=${request}`,
      method: 'GET'
    });
  };

  useEffect(() => {
    if (response && response.direccionesNormalizadas) {
      setAddress(response.direccionesNormalizadas);
    }
    if (error) {
      setErrorMessage(error);
    }
  }, [response, error]);

  const handleChange = (event) => {
    setRequest(event.target.value);
  };


  return (
    <main className={` d-flex justify-content-center align-items-center`}>
      <div className={`${styles.main}`}>
        <h2 className="col-12 fw-bold">Direcciones Sugeridas</h2>
        <Form onSubmit={sendRequest} noValidate className={styles.form}>
          <Form.Group className="">
            <Form.Label>Ingresar Direccio</Form.Label>
            <Form.Control
              type="text"
              placeholder="Calle y/o numero"
              name="direccion"
              value={request}
              onChange={handleChange}
            />
          </Form.Group>
          <Button variant="warning" type="submit" className="mt-3 " >
            Buscar
          </Button>
        </Form>
        {/* {address.length > 0 && <p className="p-3">Resultados:</p>} */}
        {errorMessage && <p className=" alert alert-danger text-center">{errorMessage} </p>}
        <div className={`${styles.responses} `}>
          {
            address.map((ad, index) =>
              <div key={index} className="p-2" >
                <p> <span class="fw-bold">Dirección:</span> {ad.direccion} </p>
                <p> <span class="fw-bold">Calle:</span> {ad.nombre_calle} </p>
              </div>
            )
          }
        </div>

      </div>
    </main>
  );
}
