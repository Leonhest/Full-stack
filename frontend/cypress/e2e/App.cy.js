/// <reference types="cypress" />




describe("App", () =>{
    beforeEach(() => {
        cy.visit('/contact')
      })

      it("Submitting without filling form shows error", () => {
        cy.findByRole("button", {name: "Submit"}).click()
        cy.contains("#errorMessage", "this is a required field")
    })

    it("Submitting with correct info gives success alert", () => {
        cy.findByRole("textbox", {name: "Name"}).type( "Leon Hesthaug")
        cy.findByRole("textbox", {name: "Email"}).type( "Leon.hesthaug@gmail.com")
        cy.findByRole("textbox", {name: "Message"}).type( "Test")
        cy.findByRole("button", {name: "Submit"}).click()
        const stub = cy.stub()  
        cy.on ('window:alert', stub)
        cy.findByRole("button", {name: "Submit"}).click().then(()=>{
            expect(stub.getCall(0)).to.be.calledWith('Success!');
        })
       
        })
 

    it("Submitting with correct info returns 201 POST response", () => {
        cy.intercept('POST', '/feedback').as('postResponse')
        cy.findByRole("textbox", {name: "Name"}).type( "Leon Hesthaug")
        cy.findByRole("textbox", {name: "Email"}).type( "Leon.hesthaug@gmail.com")
        cy.findByRole("textbox", {name: "Message"}).type( "Test")
        cy.findByRole("button", {name: "Submit"}).click()
    
        cy.wait("@postResponse").then((intercept)=>{
            const { statusCode, body } = intercept.response
   
            expect(statusCode).to.eq(201)
        })
        

    })
})