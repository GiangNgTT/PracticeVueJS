<template>
  <v-app>
      <MainHeader/>
      <router-view />
  </v-app>
</template>

<script>
import MainHeader from "@/components/MainHeader";
export default {
  name: 'App',
  components: {
    MainHeader
  },

  data: () => ({
    //
  }),
}
</script>
