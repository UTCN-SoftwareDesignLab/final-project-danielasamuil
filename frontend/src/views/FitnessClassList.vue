<template>
  <v-card>
    <v-card-title>
      Fitness Classes
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
        :items="fitnessClasses"
        :search="search"
    ></v-data-table>
  </v-card>
</template>

<script>
import api from "../api";

export default {
  name: "FitnessClassList",
  data() {
    return {
      fitnessClasses: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Price", value: "price" },
        { text: "Trainer ID", value: "trainerId" },
      ],
    };
  },
  methods: {},
  async created() {
    this.patients = await api.fitnessClass.allFitnessClasses();
  },
};
</script>

<style scoped></style>