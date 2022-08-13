import React, { useEffect, useState } from "react";
import "antd/dist/antd.css";
import { useHistory, Link, useParams} from "react-router-dom";
import { Table } from "antd";

import AuthService from "../../services/auth.service";
import WarehousesService from "../../services/warehouses.service";

import { AiFillCloseCircle } from "react-icons/ai";

const UserWarehouseTable = () => {

    const [records, setRecords] = useState([]);
    
    const [loading, setLoading] = useState(false);
    
    const currentUser = AuthService.getCurrentUser();
    const requester = currentUser.id;

    const id = useParams();
    

    const getRecords = async (user_id) => {
        try {
            setLoading(true);
            const res = await WarehousesService.getWarehouseRecords(user_id, 4);
            console.log(res.data);
            setRecords(res.data);
            setLoading(false);
        } catch (error) {
            console.log(error);
        }
    }

    const handleTableChange = (filters, sorter) => {
        getRecords({
            sortField: sorter.field,
            sortOrder: sorter.order,
            ...filters
        });
    }

    useEffect(() => {
        getRecords(requester)
    }, [])

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
            title: "Weight kg",
            dataIndex: 'weightKg'
        },
        {
            title: "Count",
            dataIndex: 'count'
        },
        {
            title: "Name",
            dataIndex: ['product', 'name'],
        },
        {
            title: "FoodType",
            dataIndex: ['product', ['foodType','name']],
            // dataIndex: 'product.foodtype.name',
        },
        {
            title: "Energy",
            dataIndex: ['product','energy'],
        },
        {
            title: "Fat",
            dataIndex: ['product','fat'],
        },
        {
            title: "protein",
            dataIndex: ['product','protein'],
        },
        {
            title: "Carbs",
            dataIndex: ['product','carbs'],
        },
        {
            title: "Package Weight",
            dataIndex: ['product','packageWeight'],
        },

        // {
        //     title: "",
        //     dataIndex: ''
        // },
    ]

    return (
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
    );
}

export default UserWarehouseTable;