<template>
  <div class="flex items-center justify-center min-h-screen">
    <div
      class="px-8 py-6 mx-4 mt-4 text-left bg-white shadow-lg md:w-2/5 lg:w-2/5 sm:w-2/5"
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
                    value="TEACHER"
                    name="list-radio"
                    required
                    v-model="userGroup"
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
                    value="SCHOOL"
                    name="list-radio"
                    required
                    v-model="userGroup"
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
          <div v-if="userGroup === 'TEACHER'">
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
              v-model="schoolName"
            />
          </div>
          <TextFormSingleLine
            label="Email"
            type="email"
            required
            v-model="email"
          />
          <TextFormSingleLine
            label="Password"
            type="password"
            required
            v-model="password"
          />
          <TextFormSingleLine
            label="Confirm Password"
            type="password"
            required
            v-model="passwordConfirmation"
          />
          <span class="text-xs text-red-400">Password must be same!</span>
          <SubmitFormButton
            name="Create Account"
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
import { RegisterRequest } from "@/models/Register";
import { UserGroup } from "@/models/User";

export default defineComponent({
  name: "RegisterView",
  components: { TextFormSingleLine, SubmitFormButton },
  data() {
    return {
      schoolName: "",
      firstname: "",
      lastname: "",
      email: "",
      password: "",
      passwordConfirmation: "",
      userGroup: "TEACHER",
      sports: [],
    };
  },
  methods: {
    async submitRegistration() {
      if (
        this.password.trim() === "" ||
        this.passwordConfirmation.trim() === "" ||
        this.email.trim() === ""
      ) {
        return;
      }

      let userToRegister: RegisterRequest;
      const userGroup: UserGroup =
        UserGroup[this.userGroup.toUpperCase() as keyof typeof UserGroup];
      if (userGroup === UserGroup.SCHOOL) {
        if (this.schoolName.trim() === "") {
          return;
        }
        userToRegister = new RegisterRequest(
          this.email,
          this.password,
          userGroup,
          this.schoolName,
          "",
          "",
          this.sports
        );
      } else {
        if (this.firstname.trim() === "" || this.lastname.trim() === "") {
          return;
        }
        userToRegister = new RegisterRequest(
          this.email,
          this.password,
          userGroup,
          "",
          this.firstname,
          this.lastname,
          this.sports
        );
      }

      try {
        const response = await fetch("/api/auth/register", {
          method: "POST",
          body: JSON.stringify(userToRegister),
          headers: { "Content-Type": "application/json; charset=UTF-8" },
        });
        if (!response.ok) {
          throw new Error(
            `could not register user. status: ${response.status} message: ${response.body}`
          );
        }
        this.$router.push({ path: "/login" });
      } catch (e) {
        console.log(e);
      }
    },
  },
});
</script>
