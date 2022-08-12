import { createRouter, createWebHistory } from 'vue-router'
import Employee from "@/components/Employee";
import AddEmployee from "@/components/AddEmployee";
// import Search from "@/components/Search";

const routes = [
    {
        path: '/employeeA',
        name: 'employees',
        component: Employee
    },
    {
        path: '/AddEmployee/:employeeId',
        name: 'employee',
        component: AddEmployee,
        props: true,
    },
    // {
    //     path: '/SearchByFirstName',
    //     name:'search',
    //     component: Search,
    //     props: true,
    // },
    {
        path: '/:catchAll(.*)',
        component: Error
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router
