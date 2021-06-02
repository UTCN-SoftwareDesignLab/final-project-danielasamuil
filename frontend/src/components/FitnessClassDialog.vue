<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ "Create fitness class" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="fitnessClass.name" label="Name"/>
          <v-text-field v-model="fitnessClass.price" label="Price"/>
          <v-text-field v-model="fitnessClass.trainerId" label="Trainer ID"/>
        </v-form>
        <v-card-actions>
          <v-btn @click="createFitnessClass">
            {{"Create"}}
          </v-btn>
          <v-btn @click="deleteFitnessClass">Delete Fitness Class</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "FitnessClassDialog",
  props: {
    fitnessClass: Object,
    opened: Boolean,
  },
  methods: {
    createFitnessClass() {
      api.fitnessClass()
          .create({
            name: this.fitnessClass.name,
            price: this.fitnessClass.price,
            trainerId: this.fitnessClass.trainerId,
          })
          .then(() => this.$emit("refresh"));
    },

    deleteFitnessClass() {
      api.fitnessClass.delete({
        id: this.fitnessClass.id,
      })
          .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.fitnessClass.id;
    },
  },
}
</script>

<style scoped>
</style>