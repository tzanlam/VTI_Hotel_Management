import { Provider } from 'react-redux'
import store from './redux/store'
import { RouterProvider } from 'react-router-dom'
import { router } from './router/router'
import { ThemeProvider } from './config/Theme'
import "@fortawesome/fontawesome-free/css/all.min.css";

function App() {

  return (
    <ThemeProvider>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
    </ThemeProvider>
  )
}

export default App
