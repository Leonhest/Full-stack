import {getByText, render, screen} from "@testing-library/vue";
import {describe, it, expect, } from "vitest"
import store from "@/store";
import router from "@/router";
import Home from "@/views/Home.vue"
import userEvent from '@testing-library/user-event'



describe("Log", ()=>{

    //const user = userEvent.setup()

    it("Log displays calculations", async()=>{
        render(Home,{
           global: {
               plugins: [store, router],
           },
       }) 
       await userEvent.click(screen.getByRole("button",{name: "3"}))
       await userEvent.click(screen.getByRole("button",{name: "-"}))
       await userEvent.click(screen.getByRole("button",{name: "1"}))
       await userEvent.click(screen.getByRole("button",{name: "="}))
       
       expect(screen.getByRole("textbox", {name: "log"}).innerHTML).toContain("3-1=2")

    })
    
})
