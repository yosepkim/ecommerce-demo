import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const ProductList = () => {

  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('products')
      .then(response => response.json())
      .then(data => {
        setProducts(data);
        setLoading(false);
      })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  const productList = products.map(product => {
    return <tr key={product.id}>
      <td style={{whiteSpace: 'nowrap'}}>{product.name}</td>
      <td>{product.description}</td>
      <td><img src={"/images/" + product.name + "/image.png"} /></td>
      <td>
        <ButtonGroup>
          <Button size="sm" color="primary" tag={Link} to={"/products/" + product.id}>View</Button>
        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        <h3>Best Selling Products</h3>
        <Table className="mt-4">
          <thead>
          <tr>
            <th width="20%">Name</th>
            <th width="20%">Description</th>
            <th></th>
            <th width="10%">Actions</th>
          </tr>
          </thead>
          <tbody>
          {productList}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default ProductList;