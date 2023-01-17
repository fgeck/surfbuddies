<template>
  <nav class="m-4" v-if="!token">
    <router-link to="/" class="mr-5">Home</router-link>
    <router-link to="/about" class="mr-5">About</router-link>
    <router-link to="/test">Test</router-link>
    <router-link to="/register" class="float-right">Register</router-link>
    <router-link to="/login" class="mr-5 float-right">Login</router-link>
  </nav>
  <nav class="m-4" v-else>
    <router-link to="/" class="mr-5">Home</router-link>
    <router-link to="/about" class="mr-5">About</router-link>
    <router-link to="/test">Test</router-link>
    <!-- Show user bookings -->
    <!-- Show user profile nav -->
    <button type="submit" class="float-right" @click="logoutUser">
      Logout
    </button>
  </nav>

  <div class="bg-gray-200">
    <router-view />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { authStore } from "./stores/auth";
import { storeToRefs } from "pinia";

export default defineComponent({
  name: "MainApp",
  setup() {
    const store = authStore();
    const { token, decodedToken } = storeToRefs(store);
    return { token, decodedToken };
  },
  methods: {
    logoutUser() {
      const store = authStore();
      store.logout();
    },
  },
});
</script>
