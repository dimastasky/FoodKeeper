import React, { useEffect, useState } from "react";
import "antd/dist/antd.css";
import { useHistory } from "react-router-dom";
import { Table } from "antd";

import ProductsService from "../../services/products.service";
import AddProduct from "./AddProduct";

import { AiFillCloseCircle } from "react-icons/ai";

const ProductTable = () => {
  const [products, setProducts] = useState([]);

  const [loading, setLoading] = useState(false);

  const getAllProducts = async () => {
    try {
      setLoading(true);
      const res = await ProductsService.getAllProducts();
      console.log(res.data);
      setProducts(res.data);
      setLoading(false);
    } catch (error) {
      console.log(error);
    }
  }

  const handleTableChange = (filters, sorter) => {
    getAllProducts({
      sortField: sorter.field,
      sortOrder: sorter.order,
      ...filters
    });
  };

  useEffect(() => {
    getAllProducts()
  }, [])

  // ---------COLUMNS-------

  const columns = [
    {
      title: "ID",
      dataIndex: 'id',
      sorter: (a, b) => a.id - b.id,
      defaultSortOrder: "descend",
      width: '3%'
    },
    {
      title: "Name",
      dataIndex: 'name',
    },
    {
      title: "Energy",
      dataIndex: 'energy'
    },
    {
      title: "Carbs",
      dataIndex: 'carbs'
    },
    {
      title: "Protein",
      dataIndex: 'protein'
    },
    {
      title: "Fat",
      dataIndex: 'fat'
    },
    {
      title: "Weight",
      dataIndex: 'weight'
    },
    {
      title: "Category",
      dataIndex: ['foodType', 'name']
    }
  ]

  let history = useHistory();
// TODO: Дизайн кнопки назад; 
// TODO: Добавление элементов
  return (
    <div>
      <div>
        <div className="head-table">
          <div className="head_form">
            <AiFillCloseCircle
              onClick={() => history.goBack()}
              className="close"
            />
            <h2 className="fontsize_head"><b>Ваши продукты</b></h2>
          </div>
          <hr align="center" width="100%" size="2" color="red" />
        </div>
      </div>
      <div className="jumbotron">
        <Table
          columns={columns}
          rowKey={(record) => record.id}
          dataSource={products}
          //pagination={pagination}
          loading={loading}
          onChange={handleTableChange}
        />
      </div>
      <div className="jumbotron">
        <AddProduct/>
      </div>
    </div>
  );


}

export default ProductTable;