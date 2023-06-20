package br.com.firstsoft.core.common

interface Mapper<Source, Target> {
    fun map(value: Source): Target
    fun map(list: List<Source>): List<Target> = list.map(this::map)
}
