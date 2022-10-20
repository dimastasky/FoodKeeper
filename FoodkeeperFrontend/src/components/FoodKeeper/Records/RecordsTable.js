import React, { useEffect, useState } from "react";
import "antd/dist/antd.css";
import { useHistory, Link, useParams } from "react-router-dom";
import { Table } from "antd";

import AuthService from "../../../services/auth.service";
import WarehousesService from "../../../services/warehouses.service";

import { AiFillCloseCircle } from "react-icons/ai";

import AddProductToWarehouses from "./CreateRecord";

// todo: Check this as TABLE https://codesandbox.io/s/jqwwuw?file=/demo.js

const UserWarehouseTable = () => {

    const columns = [
        {
            title: "ID",
            dataIndex: 'id',
            sorter: (a, b) => a.id - b.id,
            defaultSortOrder: "descend",
            width: '3%'
        },
        {
            title: "BestBefore",
            dataIndex: 'bestBefore'
        },
        {
            title: "Count",
            dataIndex: 'count'
        },
        {
            title: "Name",
            dataIndex: ['product', 'name'],
        },
        // todo: Вывести данные food type
        {
            title: "FoodType",
            key: 'product',
            dataIndex: ['foodtype', 'name'],
            //dataIndex: 'product.foodtype.name',
        },
        {
            title: "Energy",
            dataIndex: ['product', 'energy'],
        },
        {
            title: "Fat",
            dataIndex: ['product', 'fat'],
        },
        {
            title: "protein",
            dataIndex: ['product', 'protein'],
        },
        {
            title: "Carbs",
            dataIndex: ['product', 'carbs'],
        },
        {
            title: "Package Weight",
            dataIndex: ['product', 'packageWeight'],
        },
    ]

    const { id } = useParams();
    // const currentUser = AuthService.getCurrentUser();
    const requester = AuthService.getCurrentUser().id;
    const [records, setRecords] = useState([]);

    const [loading, setLoading] = useState(false);
    const getRecords = async () => {
        try {
            setLoading(true);
            const res = await WarehousesService.getWarehouseRecords(requester, id);
            console.log(res.data);
            setRecords(res.data);
            setLoading(false);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        getRecords(requester)
    }, [])

    const handleTableChange = (filters, sorter) => {
        getRecords({
            sortField: sorter.field,
            sortOrder: sorter.order,
            ...filters
        });
    };

    let history = useHistory();

    return (
        <div>
            <div>
                <div className="head-table">
                    <div className="head_form">
                        <AiFillCloseCircle
                            onClick={() => history.goBack()}
                            className="close"
                        />
                        <h2 className="fontsize_head"><b>Ваши склады {requester}</b></h2>
                    </div>
                    <hr align="center" width="100%" size="2" color="red" />
                </div>
            </div>
            <div className="jumbotron">
                <h2>Добавить продукт на склад</h2>
                <AddProductToWarehouses/>
            </div>
            <div className="jumbotron">
                <Table
                    columns={columns}
                    rowKey={(record) => record.id}
                    dataSource={records}
                    //pagination={pagination}
                    loading={loading}
                    onChange={handleTableChange}
                />
            </div>
        </div>

    );
}

export default UserWarehouseTable;