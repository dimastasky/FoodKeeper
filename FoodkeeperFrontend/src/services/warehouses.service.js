import axios from "axios";

const API_URL = "http://localhost:8080/api/warehouse";

const getAllWarehouses = () => {
    return axios.get(API_URL + "/all-warehouses");
}

const getAllUserWarehouses = (userRequest) => {
    return axios.post(API_URL + "/all-user-warehouses", {
        userRequest
    });
}

const getWarehouse = (id) => {
    return axios.get(API_URL + "/warehouse/" + id);
}

const createWarehouse = (id) => {
    return axios.post(API_URL + "/warehouse");
}


const deleteWarehouse = (id) => {
    return axios.delete(API_URL + "/warehouse/" + id);
}

const addRecordToWarehouse = (user, warehouse_id, product, count, bestBefore) => {
    return axios.post(API_URL + "/warehouse/" + warehouse_id + "/add_record", {
        user,
        product,
        count,
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

};