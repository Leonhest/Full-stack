import {getByText, render, screen} from "@testing-library/vue";
import {describe, it, expect} from "vitest"
import store from "@/store";
import router from "@/router";
import Contact from "@/views/Contact.vue";
import userEvent from '@testing-library/user-event'



describe("Contact", ()=>{



    it("Contact form displays all fields", async()=>{
        render(Contact,{
           global: {
               plugins: [store, router],
           },
       }) 
       screen.getByLabelText("Name")
       screen.getByLabelText("Email")
       screen.getByLabelText("Message")

    })

    it("Contact form displays input", async()=>{
        render(Contact,{
           global: {
               plugins: [store, router],
           },
       }) 
       await userEvent.type(screen.getByLabelText("Name"), "Leon")
       expect(screen.getByLabelText("Name").value).toContain("Leon")

    })
    

})