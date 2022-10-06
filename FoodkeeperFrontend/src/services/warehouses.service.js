import axios from "axios";

const API_URL = "http://localhost:8080/api/warehouse";

const getAllWarehouses = () => {
    return axios.get(API_URL + "/all-warehouses");
}

const getAllUserWarehouses = (user) => {
    return axios.post(API_URL + "/all-user-warehouses", {
        user
    });
}

const getWarehouse = (id) => {
    return axios.get(API_URL + "/warehouse/" + id);
}


const createWarehouse = (name, warehouseType) => {
    return axios.post(API_URL + "/warehouse");
}

const getAllWarehouseTypes = () => {
    return axios.get(API_URL + "/warehouse-types");
}

const deleteWarehouse = (id) => {
    return axios.delete(API_URL + "/warehouse/" + id);
}

const addRecordToWarehouse = (user, warehouse_id, product, quantity, bestBefore) => {
    return axios.post(API_URL + "/warehouse/" + warehouse_id + "/add_record", {
        user,
        product,
        quantity,
        bestBefore,
    })
}

const getWarehouseRecords = (user, warehouse_id) => {
    return axios.post(API_URL + "/warehouse/" + warehouse_id + "/records", {
        user,
    })
}


export default {
    getAllWarehouses,
    getAllUserWarehouses,
    getWarehouse,
    createWarehouse,
    deleteWarehouse,
    addRecordToWarehouse,
    getWarehouseRecords,
    getAllWarehouseTypes,

};