<template>
  <v-card>
    <v-card-title>
      Class Subscriptions
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="classSubscriptions"
        :search="search"
    ></v-data-table>
  </v-card>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationList",
  data() {
    return {
      classSubscriptions: [],
      search: "",
      headers: [
        {
          text: "Fitness class ID",
          align: "start",
          sortable: false,
          value: "fitnessClassId",
        },
        { text: "Customer ID", value: "customerId" },
      ],
    };
  },
  methods: {},
  async created() {
    this.classSubscriptions = await api.classSubscription.allClassSubscriptions();
  },
};
</script>

<style scoped></style>