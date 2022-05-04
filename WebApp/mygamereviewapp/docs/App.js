import React, { Component } from "react";
import { Provider as StyletronProvider, DebugEngine } from "styletron-react";
import { Client as Styletron } from "styletron-engine-atomic";

const debug =
  process.env.NODE_ENV === "production" ? void 0 : new DebugEngine();

// 1. Create a client engine instance
const engine = new Styletron();

import { Div, StyleReset, ThemeProvider } from "atomize";

const theme = {
  colors: {
    black900: "#1d1d1e"
  }
};

class App extends Component {
  render() {
    return (
      <StyletronProvider value={engine} debug={debug} debugAfterHydration>
        <ThemeProvider theme={theme}>
          <StyleReset />
          <Div
            textColor="black900"
            minH="100vh"
            w="100vw"
            d="flex"
            flexDir="column"
            justify="center"
            align="center"
            textSize="display2"
            fontFamily="secondary"
            textWeight="500"
            p={{ x: "1rem", y: "4rem" }}
          >
            Start from here
          </Div>
        </ThemeProvider>
      </StyletronProvider>
    );
  }

  navbar() {
    return (
        <>
          <Navbar bg="primary" variant="dark">
            <Container>
              <Navbar.Brand href="#home">Navbar</Navbar.Brand>
              <Nav className="me-auto">
                <Nav.Link href="#home">Home</Nav.Link>
                <Nav.Link href="#features">Features</Nav.Link>
                <Nav.Link href="#pricing">Pricing</Nav.Link>
              </Nav>
            </Container>
          </Navbar>
        </>
    );
  }
}

export default App;
