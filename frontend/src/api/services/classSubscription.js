import authHeader, { BASE_URL, HTTP } from "../http";

export default{
    allClassSubscriptions() {
        return HTTP.get(BASE_URL + "/subscriptions", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(classSubscription) {
        return HTTP.post(BASE_URL + "/subscriptions", classSubscription,{ headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(classSubscription){
        return HTTP.delete(BASE_URL + "/subscriptions/" + classSubscription.id, { headers: authHeader() }).then();
    },
    deleteAll() {
        return HTTP.delete(BASE_URL + "/subscriptions", {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },
    csv() {
        return HTTP.get(BASE_URL + "/subscriptions/export/CSV", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    pdf() {
        return HTTP.get(BASE_URL + "/subscriptions/export/PDF", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
}