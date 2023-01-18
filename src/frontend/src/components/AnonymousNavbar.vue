<template>
  <nav class="m-4">
    <router-link to="/" class="mr-5">Home</router-link>
    <router-link to="/about" class="mr-5">About</router-link>
    <router-link to="/test">Test</router-link>
    <router-link to="/register" class="float-right">Register</router-link>
    <router-link to="/login" class="mr-5 float-right">Login</router-link>
  </nav>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "AnonymousNavbar",
});
</script>
