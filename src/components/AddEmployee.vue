<template>
  <form class="form-input">
    <v-container fluid>
      <v-row>
        <v-col cols="8">
          <v-text-field
              label="First Name"
              v-model="formData.firstName"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="8">
          <v-text-field
              label="Last Name"
              v-model="formData.lastName"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="8">
          <v-text-field
              label="Email"
              v-model="formData.email"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="4">
          <v-btn
              flat
              color="secondary"
          >
            Show
          </v-btn>
        </v-col>

        <v-col cols="8">
          <v-btn
              flat
              color="error"
              @click="submit()"
          >
            Save
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </form>
</template>

<script>
import axios from 'axios'

export default {
  name: "AddEmployee",

  props: {
    updatingEmployee: Object,
    employeeId: String,
  },

  data(){
    return {
      formData: {
        firstName: '',
        lastName: '',
        email: ''
      },
    }
  },

  async mounted() {
    if (this.isEditingEmployee) {
      await this.$_getEmployeeInformation();
    }
  },
  watch: {
    updatingEmployee(formData) {
      if (formData) {
        this.formData = formData;
      }
    }
  },

  computed: {
    isEditingEmployee() {
      return this.employeeId !== 'new';
    },
  },

  methods:{
    submit() {
      console.log('submit');
      let formData = {
        id:this.formData.id,
        firstName: this.formData.firstName,
        lastName: this.formData.lastName,
        email: this.formData.email
      }
      if (!this.isEditingEmployee) {
        this.createPost(formData);
      } else {
        console.log('update employee', formData);
        this.updateEmployee(formData);
        console.log(formData)
      }
    },

    createPost(newEmployee){
      axios.post('http://localhost:8090/api/v1/employees', newEmployee )
          .then((response) => {
            this.employees = response.data;
            this.$router.push({
              name: 'employees',
            });
          })
          .catch((error) => {
            console.log(error);
          });
    },

    async updateEmployee(formData){
      axios.put(`http://localhost:8090/api/v1/employees/` + this.employeeId, formData)
          .then(response => {
            console.log(response);
            this.$router.push({
              name: 'employees',
            });
          })
          .catch(function (error) {
            console.log(error.response)
          })
    },

    async $_getEmployeeInformation(){
      axios.get(`http://localhost:8090/api/v1/employees/` + this.employeeId)
        .then(response => {
          this.formData = response.data;
        })
        .catch(function (error) {
          console.log(error.response)
        })
    },
 },
};
</script>

<style scoped>
  .form-input{
    padding:2% 10% 0 30%;
  }
</style>