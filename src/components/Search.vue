<!--<template>-->
<!--  <div class="search-wrapper">-->
<!--    <v-text-field type="text" v-model="search" placeholder="Search title.."/>-->
<!--    <label>Search title:</label>-->
<!--  </div>-->
<!--  <div>-->
<!--    <v-table>-->
<!--      <thead>-->
<!--      <tr>-->
<!--        <th class="text-left">-->
<!--          First Name-->
<!--        </th>-->
<!--        <th class="text-left">-->
<!--          Last Name-->
<!--        </th>-->
<!--      </tr>-->
<!--      </thead>-->
<!--      <tbody>-->
<!--      <tr-->
<!--          v-for="employee in filteredList"-->
<!--          :key="employee.name"-->
<!--      >-->
<!--        <td>{{ employee.firstName }}</td>-->
<!--        <td>{{ employee.lastName }}</td>-->
<!--      </tr>-->
<!--      </tbody>-->
<!--    </v-table>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "SearchByFirstName",-->

<!--  data() {-->
<!--    return {-->
<!--      employees: [],-->
<!--      search:"",-->
<!--      props: {-->
<!--        show: {-->
<!--          type: Function,-->
<!--          default() {-->
<!--            return function () {};-->
<!--          }-->
<!--        }-->
<!--      },-->
<!--      methods: {-->
<!--        buttonClicked: function () {-->
<!--          this.show();-->
<!--        }-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--  computed: {-->

<!--    filteredList() {-->
<!--      return this.employees.filter(employee => {-->
<!--        return employee.firstName.toLowerCase().includes(this.search.toLowerCase())  && employee.lastName.toLowerCase().includes(this.search.toLowerCase())-->
<!--      })-->
<!--    }-->
<!--  },-->
<!--  methods:{-->

<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->