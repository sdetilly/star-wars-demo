package com.tillylabs.star_wars_demo.people

import android.view.View
import com.tillylabs.star_wars_demo.NameAdapter
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RowPersonVMTest {

    lateinit var vm: RowPersonVM

    @Test
    fun getName() {
        vm = RowPersonVM(Person(name = "Phil"))
        assertEquals("Phil", vm.getName())

        val listener = mock(NameAdapter.ItemClickListener::class.java)

        //make sure getName is good with non null listener
        vm = RowPersonVM(Person(name = "Sam"), listener)
        assertEquals("Sam", vm.getName())
    }

    @Test
    fun onItemClick() {
        val listener = mock(NameAdapter.ItemClickListener::class.java)
        val view = mock(View::class.java)
        val personName = "Phil"
        vm = RowPersonVM(Person(name = personName), listener)
        verify(listener, Mockito.never()).onItemClicked(view, personName)
        vm.onItemClick().onClick(view)
        verify(listener, Mockito.atLeastOnce()).onItemClicked(view, personName)
    }
}