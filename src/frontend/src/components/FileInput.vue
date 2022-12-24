<template>
  <div class="my-4">
    <label for="{{ label }}" class="block">{{ label }}</label>
    <input type="file" @change="uploadFile" ref="file" />
    <button @click="submitFile">Upload!</button>
  </div>
</template>

<script></script>
