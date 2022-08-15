<template>
  <div class="container">
    <v-container>
      <v-switch
          v-model="model"
          :label="`Switch: ${model.toString()}`"
          color="primary"
      ></v-switch>
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
            v-for="employee in employees"
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
    </v-container>
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
      model: true,
      isDeleted:true
    }
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
      axios.delete(`http://localhost:8090/api/v1/employees/` +id, this.employees)
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

