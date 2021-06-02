import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allFitnessClasses() {
        return HTTP.get(BASE_URL + "/fitness-classes", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(fitnessClass) {
        return HTTP.post(BASE_URL + "/fitness-classes", fitnessClass,{ headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(fitnessClass){
        return HTTP.delete(BASE_URL + "/fitness-classes" + fitnessClass.id, { headers: authHeader() }).then();
    },
    deleteAll() {
        return HTTP.delete(BASE_URL + "/fitness-classes", {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },
    edit(fitnessClass) {
        return HTTP.put(BASE_URL + "/fitness-classes" + fitnessClass.id, fitnessClass,{ headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
};