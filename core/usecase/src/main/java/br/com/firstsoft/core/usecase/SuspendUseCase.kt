package br.com.firstsoft.core.usecase

interface SuspendUseCase<out Type, in Params> {
    suspend operator fun invoke(params: Params): Type
}

interface SuspendUseCaseWithoutParams<out Type> {
    suspend operator fun invoke(): Type
}
