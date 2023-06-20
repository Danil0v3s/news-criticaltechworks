package br.com.firstsoft.core.usecase

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<out Type, in Params> {
    operator fun invoke(params: Params): Flow<Type>
}

interface FlowUseCaseWithoutParams<out Type> {
    operator fun invoke(): Flow<Type>
}

interface SuspendFlowUseCaseWithoutParams<out Type> {
    suspend operator fun invoke(): Type
}
