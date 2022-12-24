<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-200">
    <div
      class="px-8 py-6 mx-4 mt-4 text-left bg-white shadow-lg md:w-1/3 lg:w-1/3 sm:w-1/3"
    >
      <h3 class="text-2xl font-bold text-center mb-4">
        Register at Surfbuddies
      </h3>
      <form action="">
        <div class="">
          <div>
            <ul
              class="items-center w-full text-sm font-medium rounded-lg border sm:flex"
            >
              <li class="w-full">
                <div class="flex items-center pl-3">
                  <input
                    type="radio"
                    value="teacher"
                    name="list-radio"
                    v-model="type"
                    class="w-4 h-4 text-blue-600 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 focus:ring-2"
                  />
                  <label
                    for="teacher"
                    class="py-3 ml-2 w-full text-sm font-medium"
                    >Teacher
                  </label>
                </div>
              </li>
              <li class="w-full">
                <div class="flex items-center pl-3">
                  <input
                    type="radio"
                    value="school"
                    name="list-radio"
                    v-model="type"
                    class="w-4 h-4 text-blue-600 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 focus:ring-2"
                  />
                  <label
                    for="school"
                    class="py-3 ml-2 w-full text-sm font-medium"
                    >Surfschool</label
                  >
                </div>
              </li>
            </ul>
          </div>
          <div v-if="type === 'teacher'">
            <TextFormSingleLine
              label="Firstname"
              type="text"
              v-model="firstname"
            />
            <TextFormSingleLine
              label="Lastname"
              type="text"
              v-model="lastname"
            />
          </div>
          <div v-else>
            <TextFormSingleLine
              label="Name of Surfschool"
              type="text"
              v-model="surfschoolName"
            />
          </div>
          <TextFormSingleLine label="Email" type="text" v-model="email" />
          <TextFormSingleLine
            label="Password"
            type="password"
            v-model="password"
          />
          <TextFormSingleLine
            label="Confirm Password"
            type="password"
            v-model="passwordConfirmation"
          />
          <span class="text-xs text-red-400">Password must be same!</span>
          <SubmitFormButton
            name="Creat Account"
            @buttonClicked="submitRegistration"
          />
          <div class="mt-6 text-grey-dark">
            Already have an account?
            <router-link to="/login" class="text-blue-500 hover:underline"
              >Login</router-link
            >
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import SubmitFormButton from "../components/SubmitFormButton.vue";
import TextFormSingleLine from "../components/TextFormSingleLine.vue";

export default defineComponent({
  name: "RegisterView",
  components: { TextFormSingleLine, SubmitFormButton },
  data() {
    return {
      surfschoolName: "",
      firstname: "",
      lastname: "",
      email: "",
      password: "",
      passwordConfirmation: "",
      type: "teacher",
    };
  },
  methods: {
    submitRegistration() {
      if (
        this.password.trim() === "" ||
        this.passwordConfirmation.trim() === "" ||
        this.email.trim() === ""
      ) {
        return;
      }
      if (this.type === "teacher") {
        if (this.firstname.trim() === "" || this.lastname.trim() === "") {
          return;
        }
      } else {
        if (this.surfschoolName.trim() === "") {
          return;
        }
      }

      console.log(
        `Registering new user: ${this.firstname} - ${this.lastname} - ${this.email} - ${this.password}`
      );
    },
  },
});
</script>
