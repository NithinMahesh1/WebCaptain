<script setup lang="ts">
import axios from 'axios';

defineProps<{
  msg: string
}>()

function getTokenRequest() {
  console.log("Running youtube id_token request")

  // May need to do our testing with api key if we can't add authorized uris to dev console
  const res = axios.request({
    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
    url: "/oauth2/token",
    method: "post",
    baseURL: "https://oauth2.googleapis.com/token",
    data: "grant_type=client_credentials",
    auth: {
      // Add client_id
      username: "",
      // Add client_secret 
      password: "" 
    }
  });

  console.log(res);
}
</script>

<template>
  <div class="greetings">
    <h1 class="green">{{ msg }}</h1>
    <h3>
      You’ve successfully created a project with
      <a href="https://vitejs.dev/" target="_blank" rel="noopener">Vite</a> +
      <a href="https://vuejs.org/" target="_blank" rel="noopener">Vue 3</a>. What's next?
    </h3>
  </div>
  <div>
    <button v-on:click="getTokenRequest" >Run Token Request</button>
  </div>
</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

.greetings h1,
.greetings h3 {
  text-align: center;
}

@media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
}
</style>
