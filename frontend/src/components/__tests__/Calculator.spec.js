import {getByText, render, screen} from "@testing-library/vue";
import {describe, it, expect, } from "vitest"
import store from "@/store";
import router from "@/router";
import Calculator from "@/components/Calculator.vue"
import userEvent from '@testing-library/user-event'



describe("Calculator", ()=>{

    //const user = userEvent.setup()

    it("Calculator displays 1 when 1 button is pressed", async()=>{
        render(Calculator,{
           global: {
               plugins: [store, router],
           },
       }) 
       await userEvent.click(screen.getByText('1'))
       expect(screen.getByRole("textbox").innerHTML).toBe("1")

    })
    it("3 + 4 equals 7", async()=>{
        render(Calculator,{
           global: {
               plugins: [store, router],
           },
       }) 
       await userEvent.click(screen.getByText('3'))
       await userEvent.click(screen.getByText('+'))
       await userEvent.click(screen.getByText('4'))
       await userEvent.click(screen.getByText('='))
       expect(screen.getByRole("textbox").innerHTML).toBe("7")

    })

    it("+++ gives NaN output", async()=>{
        render(Calculator,{
           global: {
               plugins: [store, router],
           },
       }) 
       
       await userEvent.tripleClick(screen.getByText('+'))
       await userEvent.click(screen.getByText('='))
       expect(screen.getByRole("textbox").innerHTML).toBe("NaN")
    })

    it("C removes all input", async()=>{
        render(Calculator,{
           global: {
               plugins: [store, router],
           },
       }) 
       
       await userEvent.click(screen.getByText('3'))
       await userEvent.click(screen.getByText('x'))
       await userEvent.click(screen.getByText('4'))
       await userEvent.click(screen.getByText('C'))
       expect(screen.getByRole("textbox").innerHTML).toBe("")
       
    })

    it("Del removes last input", async()=>{
        render(Calculator,{
           global: {
               plugins: [store, router],
           },
       }) 
       
       await userEvent.click(screen.getByText('3'))
       await userEvent.click(screen.getByText('/'))
       await userEvent.click(screen.getByText('4'))
       await userEvent.click(screen.getByText('Del'))
       expect(screen.getByRole("textbox").innerHTML).toBe("3/")
       
    })

    it("Decimal calculations yield correct result", async()=>{
        render(Calculator,{
           global: {
               plugins: [store, router],
           },
       }) 
       
       await userEvent.click(screen.getByText('3'))
       await userEvent.click(screen.getByText('.'))
       await userEvent.click(screen.getByText('1'))
       await userEvent.click(screen.getByText('x'))
       await userEvent.click(screen.getByText('2'))
       await userEvent.click(screen.getByText('='))
       expect(screen.getByRole("textbox").innerHTML).toBe("6.2")
       
    })

})
