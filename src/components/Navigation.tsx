import { Button } from '@/components/ui/button';
import { ArrowRight, Menu, X } from 'lucide-react';
import { useState } from 'react';

export function Navigation() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <nav className="fixed top-0 left-0 right-0 z-50 bg-white/95 backdrop-blur-sm border-b border-gray-100">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          <div className="flex items-center">
            <div className="flex-shrink-0">
              <span className="text-2xl font-bold text-gray-900">handoff</span>
            </div>
          </div>
          
          <div className="hidden md:block">
            <div className="ml-10 flex items-baseline space-x-8">
              <a href="#how-it-works" className="text-gray-600 hover:text-gray-900 px-3 py-2 text-sm font-medium transition-colors">
                How it Works
              </a>
              <a href="#creators" className="text-gray-600 hover:text-gray-900 px-3 py-2 text-sm font-medium transition-colors">
                For Creators
              </a>
              <a href="#finishers" className="text-gray-600 hover:text-gray-900 px-3 py-2 text-sm font-medium transition-colors">
                For Finishers
              </a>
              <a href="#case-studies" className="text-gray-600 hover:text-gray-900 px-3 py-2 text-sm font-medium transition-colors">
                Case Studies
              </a>
            </div>
          </div>

          <div className="hidden md:flex items-center space-x-4">
            <Button variant="ghost" size="sm">
              Sign In
            </Button>
            <Button size="sm" className="bg-blue-600 hover:bg-blue-700">
              Get Started
              <ArrowRight className="ml-2 h-4 w-4" />
            </Button>
          </div>

          <div className="md:hidden">
            <Button
              variant="ghost"
              size="sm"
              onClick={() => setIsMenuOpen(!isMenuOpen)}
            >
              {isMenuOpen ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
            </Button>
          </div>
        </div>
      </div>

      {isMenuOpen && (
        <div className="md:hidden">
          <div className="px-2 pt-2 pb-3 space-y-1 sm:px-3 bg-white border-b border-gray-100">
            <a href="#how-it-works" className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium">
              How it Works
            </a>
            <a href="#creators" className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium">
              For Creators
            </a>
            <a href="#finishers" className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium">
              For Finishers
            </a>
            <a href="#case-studies" className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium">
              Case Studies
            </a>
            <div className="px-3 py-2 space-y-2">
              <Button variant="ghost" size="sm" className="w-full">
                Sign In
              </Button>
              <Button size="sm" className="w-full bg-blue-600 hover:bg-blue-700">
                Get Started
                <ArrowRight className="ml-2 h-4 w-4" />
              </Button>
            </div>
          </div>
        </div>
      )}
    </nav>
  );
}
