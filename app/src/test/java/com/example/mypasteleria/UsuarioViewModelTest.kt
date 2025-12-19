package com.example.mypasteleria

import com.example.mypasteleria.ViewModel.UsuarioViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UsuarioViewModelTest : StringSpec({

    "login correcto debe retornar true" {
        val vm = UsuarioViewModel()

        vm.registrarUsuarioLocal(
            nombre = "Pedro",
            correo = "pedro@gmail.com",
            clave = "123456",
            direccion = "Calle 123"
        ) shouldBe true

        vm.loginConBackend("pedro@gmail.com", "123456") shouldBe true
    }

    "login incorrecto debe retornar false" {
        val vm = UsuarioViewModel()

        vm.registrarUsuarioLocal(
            nombre = "Pedro",
            correo = "pedro@gmail.com",
            clave = "123456",
            direccion = "Calle 123"
        )

        vm.loginConBackend("pedro@gmail.com", "malaClave") shouldBe false
        vm.loginConBackend("otro@gmail.com", "123456") shouldBe false
    }

    "cerrar sesion debe limpiar el usuario" {
        val vm = UsuarioViewModel()

        vm.registrarUsuarioLocal(
            nombre = "Pedro",
            correo = "pedro@gmail.com",
            clave = "123456",
            direccion = "Calle 123"
        )

        vm.cerrarSesion()
        vm.loginConBackend("pedro@gmail.com", "123456") shouldBe false
    }
})
