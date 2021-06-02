<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{"Create Class Subscription"}}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="classSubscription.fitnessClassId" label="Fitness class ID" />
          <v-text-field v-model="classSubscription.customerId" label="User's ID" />
        </v-form>
        <v-card-actions>
          <v-btn @click="createClassSubscription">
            {{"Create"}}
          </v-btn>
          <v-btn @click="deleteClassSubscription">Delete Class Subscription</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
export default {
  name: "ClassSubscriptionDialog",
  props: {
    classSubscription: Object,
    opened: Boolean,
  },
  data() {
    return {
      connected: false,
    }
  },
  methods: {
    createClassSubscription() {
      api.classSubscription.create({
        fitnessClassId: this.fitnessClassId,
        customerId: this.customerId,
      })
          .then(() => this.$emit("refresh"));
    },

    deleteClassSubscription() {
      api.classSubscription.delete({
        id: this.classSubscription.id,
      })
          .then(() => this.$emit("refresh"));
    },

    computed: {
      isNew: function () {
        return !this.classSubscription.id;
      },
    },
  },
};
</script>

<style scoped>
</style>