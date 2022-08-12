<template>
  <div class="container">
    <v-table>
      <thead>
      <tr>
        <th class="text-left">
          First Name
        </th>
        <th class="text-left">
          Last Name
        </th>
        <th class="text-left">
          Email
        </th>
        <th class="text-left">
          Action
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="employee in filteredEmployees"
          :key="employee.name"
      >
        <td>{{ employee.firstName }}</td>
        <td>{{ employee.lastName }}</td>
        <td>{{ employee.email }}</td>
        <td>
            <v-btn
                color="success"
                plain
                @click="updateEmployee(employee.id)"
            >
              Update
            </v-btn>

          <v-btn
              color="error"
              plain
              @click="deleteEmployee(employee.id)"
          >
            Delete
          </v-btn>
        </td>
      </tr>
      </tbody>
    </v-table>
  </div>
  <div class="search-wrapper">
    <v-text-field type="text" v-model="search" placeholder="Search title.."/>
    <label>Search title:</label>
  </div>
<!--  <div class="switch">-->
<!--    <v-switch v-model="order" true-value="-1" false-value="0" label="Toggle order" ></v-switch>-->
<!--    <input class="form-control w-25" placeholder="Filter By first name" v-model="searchClient" type="search">-->
<!--  </div>-->
  <div>
    <v-table>
      <thead>
      <tr>
        <th class="text-left">
          First Name
        </th>
        <th class="text-left">
          Last Name
        </th>
        <th class="text-left">
         Email
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="employee in filteredEmployees"
          :key="employee.name"
      >
        <td>{{ employee.firstName }}</td>
        <td>{{ employee.lastName }}</td>
        <td>{{ employee.email }}</td>
      </tr>
      </tbody>
    </v-table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "employeeA",

  data() {
    return {
      updatingEmployee:{},
      employees: [],
      order: 0,
      search:""
    }
  },
  computed: {
    filteredEmployees() {
      if (!this.search) {
        return this.employees;
      } else {
        return this.employees.filter(employee => {
          return employee.firstName.toLowerCase().includes(this.search.toLowerCase())
        })
      }
    }
    // filteredList() {
    //   return this.employees.filter(employee => {
    //     return employee.firstName.toLowerCase().includes(this.search.toLowerCase()) || employee.lastName.toLowerCase().includes(this.search.toLowerCase()) || employee.email.toLowerCase().includes(this.search.toLowerCase())
    //   })
    // }
  },
  methods: {
    async show() {
      await axios.get('http://localhost:8090/api/v1/employees')
          .then((response) => {
            console.log(response.data);
            this.employees = response.data;
            console.log('employees', this.employees);
          })
          .catch((error) => {
            console.log(error);
          });
    },
    updateEmployee(id) {
      console.log('update')
      this.$router.push({
        name: 'employee',
        params: {
          employeeId: id,
        }
      });
    },

    deleteEmployee(id){
      axios.delete(`http://localhost:8090/api/v1/employees/` +id)
          .then(response => {
            console.log(response);
          });
      this.show();
    },
  },
  ///
  updatingEmployee(id) {
    this.$emit('update-employee', id);
  },

  async mounted() {
    console.log('mounted');
    await this.show();
  },
}

</script>

<style scoped>
  .container{
    display: flex;
    text-align: center;
    padding:0 5% 0 20%;

  }
  .switch{
    padding: 2% 10% 0 20%;
  }
  v-table thead {
    padding: 30%;
  }

  v-table thead th {
    padding: 30px;
  }

  v-table tbody td {
    padding: 30px;
  }
</style>

