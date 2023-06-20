package br.com.firstsoft.core.usecase

interface UseCase<out Type, in Params> {
    operator fun invoke(params: Params): Type
}

interface UseCaseWithoutParams<out Type> {
    operator fun invoke(): Type
}
