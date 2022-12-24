<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-200">
    <div
      class="px-8 py-6 mx-4 mt-4 text-left bg-white shadow-lg md:w-1/3 lg:w-1/3 sm:w-1/3"
    >
      <h3 class="text-2xl font-bold text-center mb-4">Login at Surfbuddies</h3>
      <form action="">
        <div class="">
          <TextFormSingleLine label="Email" type="text" v-model="email" />
          <TextFormSingleLine
            label="Password"
            type="password"
            v-model="password"
          />
          <SubmitFormButton name="Login" @buttonClicked="submitLogin" />
          <div class="mt-6 text-grey-dark">
            Not registered yet?
            <router-link to="/register" class="text-blue-500 hover:underline"
              >Register here</router-link
            >
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import TextFormSingleLine from "@/components/TextFormSingleLine.vue";
import SubmitFormButton from "@/components/SubmitFormButton.vue";
export default {
  name: "LoginView",
  components: { TextFormSingleLine, SubmitFormButton },
  props: {},
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    submitLogin() {
      if (this.email.trim() === "" || this.password.trim() === "") {
        return;
      }
      console.log(`login button was clicked ${this.email} - ${this.password}`);
    },
  },
};
</script>
